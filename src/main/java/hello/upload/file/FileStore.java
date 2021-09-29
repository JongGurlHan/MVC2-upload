package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//멀티파트 파일을 서버에 저장하는 역할을 담당
@Component
public class FileStore {

    @Value("${file.dir}") //파일저장경로
    public String fileDir;

    //파일이름을 받아서 full path를 만듦
    public String getFullPath(String filename) {
        return fileDir + filename;
    }

    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (!multipartFiles.isEmpty()) {
                storeFileResult.add(storeFile(multipartFile));
            }
        }
        return storeFileResult;
    }

    //파일 저장
    //멀티파트 파일을 받아서 업로드 파일로 바꿔줌,
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return null;
        }
        //image.png
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFileName)));
        return new UploadFile(originalFilename, storeFileName);
    }



    //서버에 저장하는 파일명 만드는 메소드 uuid를 사용해서 유일한 이름 생성
    private String createStoreFileName(String originalFilename) {
        //확장자(png)꺼냄
        String ext = extractExt(originalFilename);
        //"qwe-qwe-123-qwe"
        String uuid = UUID.randomUUID().toString();

        return uuid + "." +ext;
    }

    //확장자(png)꺼냄
    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
