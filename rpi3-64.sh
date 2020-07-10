sudo apt install gawk wget git-core diffstat unzip texinfo gcc-multilib build-essential chrpath socat libsdl1.2-dev xterm python -y

source ./oe-init-build-env rpi64

sed -i '37d' conf/local.conf

sed -i '37 a MACHINE = "raspberrypi3-64"' conf/local.conf

bitbake-layers add-layer ../meta-raspberrypi/

bitbake-layers add-layer ../meta-openembedded/meta-oe/

bitbake-layers add-layer ../meta-openembedded/meta-python/

bitbake-layers add-layer ../meta-openembedded/meta-networking/

bitbake-layers add-layer ../meta-openembedded/meta-perl/

bitbake-layers add-layer ../meta-security/

bitbake-layers add-layer ../meta-openembedded/meta-webserver/

bitbake core-image-minimal
