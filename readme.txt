html + css + jquery + jpa + mybatis + mysql

1. When share the project to gitHub, there is the exception:
    git@github.com: Permission denied (publickey)
    1.1 open git bash, run command to verify username and email:
    $ git config --global --list
    1.2 create ssh key
    ssh-keygen -t rsa -C felix.sgq@gmail.com
    1.3 open .ssh (this file will be located in C:\\Users\\your computer's name\\.ssh), copy all content
    1.4 open https://github.com/settings/keys, then click "SSH and GPG" keys, then click "new ssh key", paste the content mentioned in 1.4
    1.5 verify the access
    $ ssh -T git@github.com
    Hi guardiangel! You've successfully authenticated, but GitHub does not provide shell access.

2. 2022-05-07
 create tables,
 create basic entity
 test datasource connection.

3.2022-05-08
    user registration
    encrypt password
    self-defined annotation
    jpa configuration
    self-defined exception
    add bootstrap3 and images
4.2022-05-09
4.1 Delevop webservice demo refre to Develop_webservice_with_springboot.pdf
4.2 Modify pom.xml
4.3 Register.html
4.4 Adjust password generation policy

5.2022-05-10
5.1 Use postman to test webservice, refer to Develop_webservice_with_springboot.pdf
