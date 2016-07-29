# devopskata_ci_repo
repo that code goes into for CI demo


This repo is a demonstrate repo that can be used with the devopskata infrastructure building process.

This small project represents a java application built with Maven.  It includes junit tests, and cucumber functional tests.

The devopskata repo sets up an environment of a Jenkins server, a Build server, a QA server, and a Prod server.  The Jenkins 
server is configured to cooperate with GitHub so that any changes to the devopskata_ci_repo that are commited/pushed will trigger
a job.

This allows the construction of a pipeline that runs unit tests during build, functional tests inside QA, and if those pass, installation
into prod -- a skeletal Continuous Delivery pipeline.
