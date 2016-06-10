package de.rabea.server;

import de.rabea.request.FileParser;
import de.rabea.request.Log;
import de.rabea.request.Request;
import de.rabea.response.ResponseBody;
import de.rabea.response.ResponseBodyCreator;

public class Controller {

    private final Request request;
    private final Log log;

    public Controller(Request request, Log log) {
        this.request = request;
        this.log = log;
        logAllIncoming(request, log);
        updateFile();
    }

    public ResponseBody action() {
        return new ResponseBodyCreator(request, log).create();
    }

    private void logAllIncoming(Request request, Log log) {
        log.register(request.head());
    }

    public void updateFile() {
        if (requestToPatchFile()) {
            FileParser fileParser = new FileParser(request.directory.path + request.uri);
            fileParser.updateExistingFile(request.body);
        }
    }

    private boolean requestToPatchFile() {
        return request.isPatch() && request.hasETag();
    }
}
