package edu.scau.client.image.service;

import edu.scau.client.mapper.AquariumImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    AquariumImageMapper aquariumImageMapper;

    public String download(Long id) {
        return aquariumImageMapper.selectByPrimaryKey(id).getData();
    }

}
