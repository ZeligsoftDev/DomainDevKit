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

./platformURLConverter.sh

if [ $? -ne 0 ]; then
 exit 1;
fi

echo "platform url replaced"
# Run Maven Build

./mvnw clean verify