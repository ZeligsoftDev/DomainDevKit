#!/bin/bash
#*******************************************************************************
# Copyright (c) 2020 Northrop Grumman Systems Corporation.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#*******************************************************************************

# This script currently assumes the following directory structure:
# - this Script
# - deps
#   --> rsa91_p2
# - target-platform
#   --> ddk_target.target
#   --> ddk_target.tpd

# change working directory to my directory
cd "${0%/*}"

# Ensure that no one has changed the tpd file before proceeding.
if [ target-platform/ddk_target.tpd -nt target-platform/ddk_target.target ]; then
	echo "ddk_target.tpd is newer than ddk_target.target. Update .target file before proceeding."
	exit 1;
fi


# Swap out the platform:/ url for the absolute path that Tycho demands.
sed 's+platform:/resource/target-platform/deps/rsa91_p2+file://'`pwd`'/target-platform/deps/rsa91_p2+' target-platform/ddk_target.target > target-platform/ddk_adjustedTarget.target
