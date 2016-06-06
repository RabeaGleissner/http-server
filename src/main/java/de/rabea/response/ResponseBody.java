package de.rabea.response;

import de.rabea.request.FileParser;
import de.rabea.request.Request;
import de.rabea.server.Route;

public class ResponseBody {

    private String receivedMessage;
    private String directory;
    private Request request;
    Route route;

    public ResponseBody(Request request, String directory) {
        this.request = request;
        this.directory = directory;
        this.receivedMessage = receivedMessage();
        this.route = new Route();
    }

    public String receivedMessage() {
        if (request.hasBody()) {
            return request.body();
        } else if (request.hasUrlParams()) {
            return request.urlParams();
        } else {
            return "";
        }
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

    private byte[] listLinksToFiles() {
        if (showDirectoryContents()) {
            String links = "";
            for (String file : route.directoryContents(directory)) {
                links += "<a href=/" + fileName(file) + ">" + fileName(file) + "</a>";
            }
            return links.getBytes();
        }
        return null;
    }

    private byte[] readFileContent() {
        if (folderContainsRequestedFile()) {
            if (request.isPartial()) {
                return new FileParser(directory + request.route(), request.range()).read();
            } else {
                return new FileParser(directory + request.route()).read();
            }
        }
        return null;
    }

    private boolean folderContainsRequestedFile() {
        return route.directoryContents(directory).contains(requestedFile());
    }

    private String requestedFile() {
        return directory + route.file(request.route());
    }

    private boolean directoryHasContent() {
        return route.directoryHasContent(directory);
    }

    private String fileName(String file) {
        String[] folders = file.split("/");
        return folders[folders.length - 1];
    }

    private boolean showDirectoryContents() {
        return requestedRootRoute() && directoryHasContent();
    }

    private boolean requestedRootRoute() {
        return route.requestRoot(request.route());
    }
}