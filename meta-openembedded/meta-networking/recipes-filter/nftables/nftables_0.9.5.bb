SUMMARY = "Netfilter Tables userspace utillites"
SECTION = "net"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=d1a78fdd879a263a5e0b42d1fc565e79"

DEPENDS = "libmnl libnftnl bison-native \
           ${@bb.utils.contains('PACKAGECONFIG', 'mini-gmp', '', 'gmp', d)}"

# Ensure we reject the 0.099 version by matching at least two dots
UPSTREAM_CHECK_REGEX = "nftables-(?P<pver>\d+(\.\d+){2,}).tar.bz2"

SRC_URI = "http://www.netfilter.org/projects/nftables/files/${BP}.tar.bz2"
SRC_URI[sha256sum] = "f2c1957eabc370057c4576b8f33a71d91a6ad019b8b335abafe61c9c42cc7e16"

inherit autotools manpages pkgconfig

PACKAGECONFIG ??= "python readline"
PACKAGECONFIG[json] = "--with-json, --without-json, jansson"
PACKAGECONFIG[manpages] = "--enable-man-doc, --disable-man-doc, asciidoc-native"
PACKAGECONFIG[mini-gmp] = "--with-mini-gmp, --without-mini-gmp"
PACKAGECONFIG[python] = "--enable-python --with-python-bin=${PYTHON}, --with-python-bin="", python3"
PACKAGECONFIG[readline] = "--with-cli=readline, --without-cli, readline"
PACKAGECONFIG[xtables] = "--with-xtables, --without-xtables, iptables"

inherit ${@bb.utils.contains('PACKAGECONFIG', 'python', 'python3native', '', d)}

RRECOMMENDS_${PN} += "kernel-module-nf-tables"

PACKAGES =+ "${PN}-python"
FILES_${PN}-python = "${nonarch_libdir}/${PYTHON_DIR}"
RDEPENDS_${PN}-python = "python3-core python3-json"
