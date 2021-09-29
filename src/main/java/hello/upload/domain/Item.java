package hello.upload.domain;

import lombok.Data;

import java.util.List;

//업로드파일(db저장)
@Data
public class Item {
    private Long id;
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles; //이미지를 여러개 업로드할거라서

}
