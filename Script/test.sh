#!/bin/bash
#Program:
#   Studing Shell Scripts
#History:
#   2017/10/16 Hasaker Demo

PATH=/home/hasaker/.pyenv/plugins/pyenv-virtualenv/shims:/home/hasaker/.pyenv/shims:/home/hasaker/.pyenv/bin:/usr/local/bin:/usr/bin:/bin:/usr/local/games:/usr/games:/sbin:/usr/sbin
export PATH

read -p "Please input your name: " name
echo -e "Your name is: $name"

read -p "Please input the first number: " firstnum
read -p "Please input the second number: " secondnum
total=$((firstnum*secondnum))
echo -e "The result of $firstnum x $secondnum is $total"
