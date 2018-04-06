#!/bin/sh
set -x
for i in $(find ./* -name '*.png')
do
  convert $i -resize 128x128\!  $i
done
