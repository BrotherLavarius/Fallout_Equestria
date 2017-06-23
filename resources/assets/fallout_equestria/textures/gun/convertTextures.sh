#!/bin/sh
set -x
for i in $(find ./* -name '*.png')
do
  convert $i -resize 1024x1024\!  $i
done
