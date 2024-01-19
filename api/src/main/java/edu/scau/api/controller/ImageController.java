package edu.scau.api.controller;

import edu.scau.client.image.domain.ImageVo;
import edu.scau.client.image.service.ImageService;
import edu.scau.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/image")
@Validated
@Slf4j
@Api(value = "ImageController", tags = "图片管理接口")
public class ImageController {
    /**
     * 1.下载图片
     * 2.上传图片
     */

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

    @PostMapping("/upload")
    @ApiOperation(value = "上传图片", notes = "传入图片信息，返回图片id")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "file", value = "图片文件", required = true, paramType = "query", dataTypeClass = MultipartFile.class)
    )
    public ResultData<Long> upload(@RequestParam("file") MultipartFile file) {
        Long id = imageService.upload(file);
        return ResultData.success(id);
    }

}
