package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile {

    private String uploadFileName; //업로드한 파일명
    private String storeFileName; //시스템에 저장한 파일명 사용자 모두 같은파일명으로 업로드하면 db에 파일이 덮어질 수 있어서 uuid같은걸 만들어서 안겹치게 만들어야



}
