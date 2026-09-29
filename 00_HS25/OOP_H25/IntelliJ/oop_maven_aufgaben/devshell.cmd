@echo off
rem Copyright 2025 Hochschule Luzern Informatik.
rem
rem Licensed under the Apache License, Version 2.0 (the "License");
rem you may not use this file except in compliance with the License.
rem You may obtain a copy of the License at
rem
rem      http://www.apache.org/licenses/LICENSE-2.0
rem
rem Unless required by applicable law or agreed to in writing, software
rem distributed under the License is distributed on an "AS IS" BASIS,
rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
rem See the License for the specific language governing permissions and
rem limitations under the License.

set "VERSION=%1"
if "%VERSION%"=="" (
	set "VERSION=3.9.11"
)
set IMAGE="rgisler/maven:%VERSION%-temurin-21"
docker pull %IMAGE%
docker run -it --rm --workdir "/work" -v "%cd%:/work" -v "%homedrive%%homepath%\.m2\:/root/.m2" -v "/var/run/docker.sock:/var/run/docker.sock" %IMAGE% bash