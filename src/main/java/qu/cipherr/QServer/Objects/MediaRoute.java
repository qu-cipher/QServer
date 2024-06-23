package qu.cipherr.QServer.Objects;

import qu.cipherr.QServer.Interfaces.Handler;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MediaRoute{
    private Handler handler;
    private String path;
    private String mediaType;
    private File mediaFile;
    private Path mediaFilePath;
    private byte[] fileContent  = new byte[0];

    public MediaRoute(String path, File media, String mediaType) {
        this.path = path;
        this.mediaFile = media;
        this.mediaType = mediaType;
        this.mediaFilePath = Paths.get(mediaFile.getPath());

        this.handler = createHandler();
    }

    private Handler createHandler() {
        try {
            fileContent = Files.readAllBytes(mediaFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Handler handle = new Handler() {
            @Override
            public boolean acceptsClient(Socket clientSocket) {
                return true;
            }

            @Override
            public Response handleGet() {
                Response response = new Response();
                response.setStatus("HTTP/1.1 200 OK");
                response.setContentType(mediaType);
                response.setResponseFile(fileContent);
                response.setResponseType("File");
                return response;
//                byte[] fullResponse = response.generateFileResponseBytes();
            }

            @Override
            public Response handlePost() {
                Response response = new Response();
                response.setStatus("HTTP/1.1 200 OK");
                response.setContentType(mediaType);
                response.setResponseFile(fileContent);
                response.setResponseType("File");
                return response;
            }

            @Override
            public Response handlePut() {
                Response response = new Response();
                response.setStatus("HTTP/1.1 200 OK");
                response.setContentType(mediaType);
                response.setResponseFile(fileContent);
                response.setResponseType("File");
                return response;
            }

            @Override
            public Response handleDelete() {
                Response response = new Response();
                response.setStatus("HTTP/1.1 200 OK");
                response.setContentType(mediaType);
                response.setResponseFile(fileContent);
                response.setResponseType("File");
                return response;
            }
        };

        return handle;
    }

    public String getPath() {
        return path;
    }
    public Handler getHandler() {
        return handler;
    }
}
