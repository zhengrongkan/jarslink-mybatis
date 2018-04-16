package com.zhengrongkan.api.config;

import com.alipay.jarslink.api.ModuleConfig;
import com.alipay.jarslink.api.impl.AbstractModuleRefreshScheduler;
import com.google.common.collect.ImmutableList;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * @Author: ryan
 * @Description: JarsLink 调度器，每分钟刷新一次模块，如果模块的版本号变化则会跟新模块。
 * 实现该方法需要将jar包下载至本地。
 * @Date: Created in 下午2:21 2018/4/13
 * @Modified By:
 */
public class ModuleReforeshSchedulerImpl extends AbstractModuleRefreshScheduler {

    /**
     * jar包存放路径
     */
    private static final String PATH = "/Users/ryan/workspace/YMC-JarsLink/";


    /**
     * 配置需要检查的module
     *
     * @return
     */
    @Override
    public List<ModuleConfig> queryModuleConfigs() {
        return ImmutableList.of(
                buildPersonServiceModuleConfig()
                //, buildCarServiceModuleConfig()
        );
    }


    /**
     * 配置人员模块
     *
     * @return
     */
    public static ModuleConfig buildPersonServiceModuleConfig() {
        String version = JarVersion.getJarVersion().getVersion("person-service");
        return buildModuleConfig(new String[]{PATH + "person-service/target/person-service-" + version + ".jar", "person-service", version});
    }


    private static ModuleConfig buildModuleConfig(String[] paras) {
        ModuleConfig moduleConfig = new ModuleConfig();
        try {
            URL demoModule = new URL("file", "", -1, paras[0]);
            moduleConfig.setName(paras[1]);
            moduleConfig.setEnabled(true);
            moduleConfig.setVersion(paras[2]);
            moduleConfig.setModuleUrl(ImmutableList.of(demoModule));
            //配置注解扫描 （jarslink 1.6 特性）
            moduleConfig.addScanPackage("com.zhengrongkan.personservice.service.impl")
                    .addScanPackage("com.zhengrongkan.personservice.action")
                    .addScanPackage("com.zhengrongkan.personservice.main");
            return moduleConfig;
        } catch (MalformedURLException e) {

        }
        return moduleConfig;
    }
}
