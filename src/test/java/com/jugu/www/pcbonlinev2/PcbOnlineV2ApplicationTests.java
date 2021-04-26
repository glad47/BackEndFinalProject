package com.jugu.www.pcbonlinev2;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import io.github.yedaxia.apidocs.plugin.markdown.MarkdownDocPlugin;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class PcbOnlineV2ApplicationTests {

    @Test
    void contextLoads() {
        //api文档配置
        DocsConfig config = new DocsConfig();
        config.setProjectPath("/Users/mc/WorkProject/pcb-online-v2"); // 项目根目录
        config.setProjectName("PcbOnLineV2"); // 项目名称
        config.setApiVersion("V1.0");       // 声明该API的版本
        config.setDocsPath("/Users/mc/WorkProject/pcb-online-v2/docs"); // 生成API 文档所在目录
        config.setAutoGenerate(Boolean.TRUE);  // 配置自动生成
        config.addPlugin(new MarkdownDocPlugin());//导出markdown
        Docs.buildHtmlDocs(config); // 执行生成文档
    }

}
