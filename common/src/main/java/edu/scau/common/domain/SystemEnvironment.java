package edu.scau.common.domain;


import edu.scau.common.constant.SystemEnvironmentEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 系统环境
 */
@AllArgsConstructor
@Getter
public class SystemEnvironment {

    /**
     * 是否位生产环境
     */
    private boolean isProd;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 当前环境
     */
    private SystemEnvironmentEnum currentEnvironment;
}
