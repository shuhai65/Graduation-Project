package edu.scau.api.controller;

import edu.scau.client.image.domain.ImageVo;
import edu.scau.client.image.service.ImageService;
import edu.scau.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
@Validated
@Slf4j
@Api(value = "ImageController", tags = "图片管理接口")
public class ImageController {

    @Autowired
    ImageService imageService;

    @GetMapping("/download")
    @ApiOperation(value = "下载图片", notes = "传入图片id，返回图片信息")
    public ResultData<ImageVo> download(@RequestParam("id") Long id) {
        String data = imageService.download(id);
        ImageVo imageVo = new ImageVo();
        imageVo.setData(data);
        return ResultData.success(imageVo);
    }


}
