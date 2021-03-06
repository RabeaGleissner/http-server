package de.rabea.response.body;

import de.rabea.request.Directory;
import de.rabea.request.FileParser;
import de.rabea.response.ResponseBody;

public class FileContent implements ResponseBody {


    private final String route;
    private final String range;
    private final Directory directory;
    private final boolean partialContent;

    public FileContent(String route, String range, Directory directory, boolean partialContent) {

        this.route = route;
        this.range = range;
        this.directory = directory;
        this.partialContent = partialContent;
    }

    @Override
    public byte[] response() {
        if (folderContainsRequestedFile()) {
            if (partialContent) {
                return new FileParser(directory.path + route, range).read();
            } else {
                return new FileParser(directory.path + route).read();
            }
        }
        return new byte[0];
    }

    private boolean folderContainsRequestedFile() {
        return directory.contents.contains(requestedFile());
    }

    private String requestedFile() {
        return directory.path + route;
    }
}
