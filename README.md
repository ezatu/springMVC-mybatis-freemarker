# SpringMVC-mongoDB-freemarker
简单的springmvc整合mongodb的示例demo

1、集成springmvc框架；

2、注入MongoTemplate实例BEAN；

3、实现url拦截器；

4、简单实现了文件上传功能。

5、集成freemarker引擎，同时支持jsp，默认优先选择.ftl页面。

6、集成log4j进行日志处理。


部署环境：OSX+tomcat+CentOS7+mongoDB+IDEA

mongodb环境搭建：（OSX环境下）

1、安装homebrew，"终端"中直接输入一下命令
/usr/bin/ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"

2、等待安装完毕，执行 brew install mongodb

3、安装完毕后的提示信息部分很重要，如果没注意也可以通过命令 brew info mongodb进行查看。

4、mongodb默认没有开机启动，所以需要设置，命令：brew services start mongodb即可。

CentOS7下mongodb的安装请参照mongo官方说明进行安装（这里不再熬述）。


后续会持续更新。
