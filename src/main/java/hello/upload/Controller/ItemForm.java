package hello.upload.Controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm {

    private Long itemId;
    private String itemName;

    //이미지를 다중 업로드하기 위해
    private List<MultipartFile> imageFiles;

    private MultipartFile attachFile;
}
