#!/bin/bash
sed -i '/vt.[0-9]*/d' *.obj
sed -i '/vt..[0-9]*/d' *.obj
sed -i 's/\/[0-9]*//g' *.obj
for i in $(ls *.mtl)
do
    filename=$(ls $i|cut -d"." -f1)
    text=$filename"/minecraft_"
    sed -i 's~'"$text"'~minecraft:blocks/~g' $filename'.mtl'
    sed -i 's~.png~~g' $filename'.mtl'
	obj=$filename".obj"
json={\"forge_marker\":1,\"defaults\":{\"textures\":{},\"model\":\"fallout_equestria:"$obj"\",\"uvlock\":true,\"transform\":\"forge:default-block\"},\"variants\":{\"normal\":[{\"model\":\"fallout_equestria:"$obj"\",\"transform\":{\"rotation\":[{\"y\":0},{\"x\":0},{\"z\":0}]},\"scale\":1}],\"facing=north\":{\"model\":\"fallout_equestria:"$obj"\",\"y\":180},\"facing=south\":{\"model\":\"fallout_equestria:"$obj"\",\"y\":0},\"facing=west\":{\"model\":\"fallout_equestria:"$obj"\",\"y\":90},\"facing=east\":{\"model\":\"fallout_equestria:"$obj"\",\"y\":270}}}
 	echo $json > ../../blockstates/$filename".json"
    text=""
    filename=""
done
exit
