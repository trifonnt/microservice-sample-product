# Prerequisite

Before we can start building the application, we need to have an OpenShift free or paid account and RedHat client tools(rhc) installed. For instructions how to install rhc please read [Getting Started with OpenShift Online](https://developers.openshift.com/en/getting-started-overview.html).

# Step 1: Create DIY application
Setup connection to OpenShift Broker

```shell
	rhc setup --server openshift.redhat.com -l user@mail.com
```

To create an application using client tools, type the following command:

```shell
	rhc app create microserviceproduct diy-0.1
```

OR -- DO NTO USE IT FOR NOW

```shell
	rhc create-app microserviceproduct diy-0.1 --from-code git@github.com:trifonnt/microservice-sample-product.git
	rhc create-app microserviceproduct diy-0.1 --no-git
```

This command creates an application *microserviceproduct* using *DIY* cartridge and clones the repository to *microserviceproduct* directory.

# Step 2: Add PostgreSQL cartridge to application

The application we are creating will use PostgreSQL database, hence we need to add appropriate cartridge to the application:

```shell
	cd microserviceproduct
	rhc cartridge add postgresql-9.2
```

After creating the cartridge, it is possible to check its status with the following command:

```shell
  rhc cartridge status postgresql-9.2
```

# Step 3: Delete Template Application Source code

OpenShift creates a template project that can be freely removed:

```shell
	git rm -rf .openshift README.md diy misc
```

Commit the changes:

```shell
	git commit -am "Removed template application source code"
```

# Step 4: Pull Source code from GitHub

```shell
	git remote add upstream https://github.com/trifonnt/microservice-sample-product.git
	git pull -s recursive -X theirs upstream master
```

# Step 5: Push changes

The basic template is ready to be pushed:

```shell
	git push
```

The initial deployment (build and application startup) will take some time (up to several minutes). Subsequent deployments are a bit faster, although starting Spring Boot application may take even more than 2 minutes on small Gear:

	Tomcat started on port(s): 8080/http
	Started Application in 125.511 seconds

You can now browse to: http://microserviceproduct-<namespace>.rhcloud.com/manage/health and you should see:

	{
		"status": "UP",
		"database": "PostgreSQL",
		"hello": 1
	}

You can then browse to "/" to see the API root resource.

URLs
	http://localhost:8080/products
	
	user: user
	password: <GENERATED on startup>
