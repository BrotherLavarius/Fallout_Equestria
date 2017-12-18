#!/bin/bash
set -x
sed -i '/vt.[0-9]*/d' *.obj
sed -i '/vt..[0-9]*/d' *.obj
sed -i 's/\/[0-9]*//g' *.obj
for i in $(ls *.mtl)
do
    filename=$(ls $i|cut -d"." -f1)
    text=$filename"/minecraft_"
    sed -i 's~'"$text"'~minecraft:blocks/~g' $filename'.mtl'
    sed -i 's~.png~~g' $filename'.mtl'
    text=""
    filename=""
done
exit