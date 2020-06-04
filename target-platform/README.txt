	This maven build is configured to use a .target file to determine necessary dependencies. 
For ease of editing, the file is generated from the .tpd file in this folder. Generation 
is done using an eclipse plug-in from the following site: 

https://github.com/eclipse-cbi/targetplatform-dsl

 Ideally, the maven build would be configured to generate the .target file automatically.
 This has proved challenging. The repo above has an out-dated example for achieving this:
 	https://github.com/hmarchadour/fr.obeo.releng.targetplatform/blob/78e0d470f57bfd82ba6fe7e1c09bfeb2fdfb0180/fr.obeo.releng.targetplatform-parent/targetdefinitions/pom.xml
 
 
 Another helpful (mored modern) example - 
 	https://gitlab.simantics.org/simantics/platform/-/commit/1f2634fd8522bb00406c5c24d879ad954881a8bf.
 
 Despite these examples, we didn't have the time to figure out what was going wrong in our build.
 We learned that the modern example feature name seems to be correct. We tried adding "-data tempWS"
 to the command with no luck. We also tried switching the eclipse distribution maven uses to match htose
 used in the examples.
 
 
 For the time being, manual conversion of the .tpd should be sufficient. It only needs to change
 when a new dependency is added to the code. The .target file has been checked into the repo 
 and needs to  be updated as well (option+R on Mac).