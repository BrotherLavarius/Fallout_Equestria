#!/bin/sh
set -x
for i in $(find ./* -name '*.jpg')
do
 convert ./$i ./$i.png
done

