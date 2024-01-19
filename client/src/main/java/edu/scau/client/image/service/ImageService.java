package edu.scau.client.image.service;

import edu.scau.client.domain.AquariumImage;
import edu.scau.client.mapper.AquariumImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ImageService {
    @Autowired
    AquariumImageMapper aquariumImageMapper;

    public String download(Long id) {
        return aquariumImageMapper.selectByPrimaryKey(id).getData();
    }

    public Long upload(MultipartFile file) {
        try{
            AquariumImage aquariumImage = new AquariumImage();
            aquariumImage.setData(Arrays.toString(file.getBytes()));
            aquariumImageMapper.insert(aquariumImage);
            return aquariumImage.getId();
        } catch (IOException e) {
            throw new RuntimeException("图片上传失败");
        }
    }
}
