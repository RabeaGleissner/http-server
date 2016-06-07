package de.rabea.response;

import de.rabea.request.FileParser;
import de.rabea.request.Request;
import de.rabea.server.Router;

public class ResponseBody {

    private final String receivedMessage;
    private final String directory;
    private final Request request;
    private final Router router;

    public ResponseBody(Request request, String directory) {
        this.request = request;
        this.directory = directory;
        this.receivedMessage = receivedMessage();
        this.router = new Router();
    }

    public byte[] create() {
        if (!receivedMessage.equals("")) {
            return receivedMessage.getBytes();
        }

        if (directoryHasContent()) {
            byte[] fileContent = readFileContent();
            if (fileContent != null) return fileContent;

            byte[] urlsToFiles = listLinksToFiles();
            if (urlsToFiles != null) return urlsToFiles;
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
            for (String file : router.directoryContents(directory)) {
                links += "<a href=/" + fileName(file) + ">" + fileName(file) + "</a>";
            }
            return links.getBytes();
        }
        return null;
    }

    private byte[] readFileContent() {
        if (folderContainsRequestedFile()) {
            if (request.requestsPartialContent()) {
                return new FileParser(directory + request.route, request.range).read();
            } else {
                return new FileParser(directory + request.route).read();
            }
        }
        return null;
    }

    private boolean folderContainsRequestedFile() {
        return router.directoryContents(directory).contains(requestedFile());
    }

    private String requestedFile() {
        return directory + request.route;
    }

    private boolean directoryHasContent() {
        return router.directoryHasContent(directory);
    }

    private String fileName(String file) {
        String[] folders = file.split("/");
        return folders[folders.length - 1];
    }

    private boolean showDirectoryContents() {
        return requestedRootRoute() && directoryHasContent();
    }

    private boolean requestedRootRoute() {
        return router.requestRoot(request.route);
    }
}
