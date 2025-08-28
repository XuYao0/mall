package org.example.mall.mbg;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * MyBatis Generator 执行类
 * 用于自动生成Model类、Mapper接口和XML映射文件
 *
 * @author EMall System
 * @date 2025-08-26
 */
public class Generator {

    /**
     * 执行MyBatis Generator生成代码
     *
     * @param args 命令行参数（可选）
     */
    public static void main(String[] args) {
        try {
            System.out.println("======== MyBatis Generator 开始执行 ========");

            // 执行代码生成
            generateCode();

            System.out.println("======== MyBatis Generator 执行成功 ========");
            System.out.println("生成的文件位置：");
            System.out.println("- Model类: src/main/java/org/example/mall/mbg/model/");
            System.out.println("- Mapper接口: src/main/java/org/example/mall/mbg/mapper/");
            System.out.println("- XML映射: src/main/resources/org/example/mall/mbg/mapper/");

        } catch (Exception e) {
            System.err.println("======== MyBatis Generator 执行失败 ========");
            System.err.println("错误信息: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 执行代码生成的核心方法
     */
    private static void generateCode() throws IOException, XMLParserException,
            InvalidConfigurationException, SQLException, InterruptedException {

        // 警告信息收集器
        List<String> warnings = new ArrayList<>();

        // 允许覆盖生成的文件
        boolean overwrite = true;

        // 读取配置文件
        InputStream configFile = Generator.class.getClassLoader()
                .getResourceAsStream("generatorConfig.xml");

        if (configFile == null) {
            throw new IOException("未找到配置文件 generatorConfig.xml");
        }

        // 解析配置文件
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        configFile.close();

        // 创建回调处理器
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);

        // 创建MyBatis Generator实例
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        // 执行生成
        myBatisGenerator.generate(null);

        // 输出警告信息
        if (!warnings.isEmpty()) {
            System.out.println("======== 警告信息 ========");
            for (String warning : warnings) {
                System.out.println("WARNING: " + warning);
            }
        }
    }

    /**
     * 编程方式执行代码生成（用于在代码中调用）
     *
     * @param configPath 配置文件路径
     * @param overwrite 是否覆盖已存在的文件
     * @return 生成是否成功
     */
    public static boolean generateByConfig(String configPath, boolean overwrite) {
        try {
            List<String> warnings = new ArrayList<>();

            InputStream configFile = Generator.class.getClassLoader()
                    .getResourceAsStream(configPath);

            if (configFile == null) {
                System.err.println("配置文件不存在: " + configPath);
                return false;
            }

            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            configFile.close();

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

            myBatisGenerator.generate(null);

            return true;

        } catch (Exception e) {
            System.err.println("生成代码失败: " + e.getMessage());
            return false;
        }
    }

    /**
     * 快速生成方法（使用默认配置）
     */
    public static void quickGenerate() {
        try {
            System.out.println("使用默认配置快速生成代码...");
            generateCode();
            System.out.println("快速生成完成！");
        } catch (Exception e) {
            System.err.println("快速生成失败: " + e.getMessage());
        }
    }

    /**
     * 检查配置文件是否存在
     *
     * @return 配置文件是否存在
     */
    public static boolean checkConfigExists() {
        InputStream configFile = Generator.class.getClassLoader()
                .getResourceAsStream("generatorConfig.xml");

        if (configFile != null) {
            try {
                configFile.close();
            } catch (IOException e) {
                // 忽略关闭异常
            }
            return true;
        }
        return false;
    }

    /**
     * 显示帮助信息
     */
    public static void showHelp() {
        System.out.println("======== MyBatis Generator 使用说明 ========");
        System.out.println("1. 确保数据库连接配置正确（generator.properties）");
        System.out.println("2. 确保配置文件正确（generatorConfig.xml）");
        System.out.println("3. 运行此类的main方法生成代码");
        System.out.println("4. 生成的文件会自动放置到指定目录");
        System.out.println("===============================================");
    }
}
