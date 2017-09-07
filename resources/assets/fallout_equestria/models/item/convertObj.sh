#!/bin/bash
for i in $(find ./* -name "*.obj")
do
    echo "processing $i"
    sed -i '/vt.[0-9]*/d' $i
    sed -i '/vt..[0-9]*/d' $i
    sed -i 's/\/[0-9]*//g' $i
done
exit