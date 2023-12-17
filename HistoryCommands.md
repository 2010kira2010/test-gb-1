## Question 1
```
cat > home_animals
cat > pack_animals
cat home_animals pack_animals > animals
cat animals
mv animals friends_of_man
```
## Question 2
```
mkdir test
mv friends_of_man ./test/
ls -l ./test/*
```

## Question 3
```
wget https://dev.mysql.com/get/mysql-apt-config_0.8.29-1_all.deb
dpkg -i mysql-apt-config_0.8.29-1_all.deb
apt update
apt install mysql-server mysql-client
```

## Question 4
```
wget http://archive.ubuntu.com/ubuntu/pool/universe/s/sngrep/sngrep_1.4.6-2_amd64.deb
dpkg -i sngrep_1.4.6-2_amd64.deb
dpkg -r sngrep_1.4.6-2_amd64.deb
```

