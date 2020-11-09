[toc]
# 迁移说明
公司有一些老的项目在svn上面, svn上的代码版本、分支都不好处理, 所以也都迁移到了gitlab上面, 但是为了保留之前svn上的提交记录, 不能直接克隆下来svn代码创建本地仓库提交到git,下面步骤本人已经使用了很多次, 可以放心食用。
# 迁移SVN代码到git库方法步骤 
1. 在本地工作空间下打开git bash
2. git svn clone svn地址 新建本地仓库名(该步骤第一次操作可能会有个sshKey的页面, 直接回车或者点击ok就行),  示例: git svn clone http://xxx/xxx/.../xxx/myProjectmyProject
3. 在gitlab创建一个项目(也可以推送到已经有的远端仓库或者指定分支, 见下面补充说明), 如: myProject
4. cd myProject  进入到已经下好到本地仓库的目录下
5. git remote add origin http://gitlab.xxx.com/xxx/myProject.git
6. git push -u origin master 推送本地仓库项目到远程仓库
# 命令说明
上诉步骤是推送到新建远端仓库的默认master分支, 想要推送到指定分支, 主要修改上述5、6步就可以。  
5、git remote add origin http://..../.../xx.git(你想要关联的远端仓库, 新建的旧的都可以)  
6、git push origin 本地分支名:想要关联的本地分支名  
上述第6步是基本的git push命令:
~~~
1、推送本地分支到远端分支
    git push <远程主机名> <本地分支名>:<远程分支名>      
2、如果本地分支名和远程分支名一样, 可以省略冒号简写:
    git push <远程主机名> <本地分支名>
3、如果本地版本和远端版本有差异, 但是想要强制推送, 可以使用 --force 参数
    git push --force <远程主机名> <本地分支名>:<远程分支名>      
~~~ 