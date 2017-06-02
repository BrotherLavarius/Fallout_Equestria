#!/bin/bash
sed -i '/vt.[0-9]*/d' *.obj
sed -i '/vt..[0-9]*/d' *.obj
sed -i 's/\/[0-9]*//g' *.obj
exit