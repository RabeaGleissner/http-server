package de.rabea.response;

import de.rabea.request.Directory;
import de.rabea.request.FileParser;
import de.rabea.request.Request;
import de.rabea.server.Router;

public class ResponseBody {

    private Request request;
    private Router router;
    private Directory directory;
    private String response;

    public ResponseBody(String response) {
        this.response = response;
    }

    public ResponseBody(Request request) {
        this.request = request;
        this.router = new Router(request.directory);
        this.directory = request.directory;
        this.response = "";
    }

    public byte[] create() {
        if (!response.equals("")) {
            return response.getBytes();
        }

        if (directoryHasContent() && requestMethodAllowed()) {
            byte[] fileContent = readFileContent();
            if (fileContent != null) return fileContent;

//            byte[] urlsToFiles = listLinksToFiles();
//            if (urlsToFiles != null) return urlsToFiles;
        }

        return new byte[0];
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
