# DDK
Domain Development Kit

## Installation


#### Prerequisites

The Domain development kit requires a working installation of Rational Software Architect (RSA) 9.1 or 9.5.

#### Download

Download the latest release update site here:

	https://github.com/ZeligsoftDev/DomainDevKit/releases

#### Install

To install, run RSA as a super user. 

1. Navigate to Help > Install New Software

2. Ensure the necessary Eclipse update site is enabled. The update site does not include required dependencies, however, these can be automatically picked up by enabling the appropriate Eclipse release update site:

   For 9.1, make sure https://download.eclipse.org/releases/juno/ is configured and enabled.
   
   For 9.5, make sure https://download.eclipse.org/releases/luna/ is configured and enabled.

3. Add the downloaded zip file from github releases as an update site and install its contents.

4. Exit RSA and rerun as a regular user.