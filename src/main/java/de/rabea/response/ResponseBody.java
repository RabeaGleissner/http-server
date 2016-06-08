package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.FileParser;
import de.rabea.request.Request;
import de.rabea.server.Router;

public class ResponseBody {

    private final String receivedMessage;
    private final Request request;
    private final Router router;
    private final Directory directory;

    public ResponseBody(Request request) {
        this.request = request;
        this.receivedMessage = receivedMessage();
        this.router = new Router(request.directory);
        this.directory = request.directory;
    }

    public byte[] create() {
        if (!receivedMessage.equals("")) {
            return receivedMessage.getBytes();
        }

        if (directoryHasContent() && requestMethodAllowed()) {
            byte[] fileContent = readFileContent();
            if (fileContent != null) return fileContent;

            byte[] urlsToFiles = listLinksToFiles();
            if (urlsToFiles != null) return urlsToFiles;
        }

        if (router.routeNeedsAuthorisation(request.uri) && !request.authorisation.equals("")) {
           return "GET /log HTTP/1.1\nPUT /these HTTP/1.1\nHEAD /requests HTTP/1.1".getBytes();
        }
        return new byte[0];
    }

    public String receivedMessage() {
        if (request.hasBody()) {
            return request.body;
        } else if (request.hasUrlParams()) {
            return request.urlParams;
        } else {
            return "";
        }
    }

    private byte[] listLinksToFiles() {
        if (showDirectoryContents()) {
            String links = "";
            for (String file : directory.contents) {
                links += "<a href=/" + fileName(file) + ">" + fileName(file) + "</a>";
            }
            return links.getBytes();
        }
        return null;
    }

    private byte[] readFileContent() {
        if (folderContainsRequestedFile()) {
            if (request.requestsPartialContent()) {
                return new FileParser(directory.path + request.route, request.range).read();
            } else {
                return new FileParser(directory.path + request.route).read();
            }
        }
        return null;
    }

    private boolean folderContainsRequestedFile() {
        return directory.contents.contains(requestedFile());
    }

    private String requestedFile() {
        return directory.path + request.route;
    }

    private boolean directoryHasContent() {
        return directory.containsContent();
    }

    private String fileName(String file) {
        String[] folders = file.split("/");
        return folders[folders.length - 1];
    }

    private boolean requestMethodAllowed() {
        return router.validMethod(request.httpVerb, request.uri);
    }

    private boolean showDirectoryContents() {
        return requestedRootRoute() && directoryHasContent();
    }

    private boolean requestedRootRoute() {
        return router.requestRoot(request.route);
    }
}
