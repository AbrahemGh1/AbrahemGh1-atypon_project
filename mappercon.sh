#!bin/sh
docker run --name myubuntu -itd ubuntu
apt-get update
apt install default-jdk
\** add -y