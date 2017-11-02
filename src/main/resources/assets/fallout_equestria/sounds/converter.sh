#!/bin/bash
for i in $(find ./* -name "*" ! -name "*.ogg" ! -name "*sh")
do
#echo "$i"|cut -f2 -d'.' | cut -b2-40
ffmpeg -i $i -c:a libvorbis -q:a 4 $(echo "$i"|cut -f2 -d'.' | cut -b2-40).ogg
rm -rf $i 
done
