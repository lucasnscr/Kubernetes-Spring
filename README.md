### Project description
🚀 This project aims to create an application with microservices architecture using Spring for all development. For virtualization, Docker is used to support our database allocation and create application containers. To orchestrate our container, Kubernetes is used with Minikube that provides us with all the context of a container cluster to receive our application and its surroundings.

## Installation and  Technologies

The following technologies were used to carry out the project and it is necessary to install some items:
- Docker
- Java 11
- Maven
- MiniKube
- Spring
- Postgres
- Docker

### Kubernetes

Kubernetes is a portable, extensible, open-source platform for managing containerized workloads and services, that facilitates both declarative configuration and automation. It has a large, rapidly growing ecosystem. Kubernetes services, support, and tools are widely available.

Currently, k8s is one of the main DevOps tools, as it is normally used in production environments and application approval. However, it is also possible to create a local cluster on a development machine, which greatly facilitates the local testing of an application and also increases the reliability of a new development, since the programming environment is much closer to the development environment. production.

### O que são Pods ?
Pods are the smallest deployable units of computing that you can create and manage in Kubernetes.

A Pod (as in a pod of whales or pea pod) is a group of one or more containers, with shared storage and network resources, and a specificationfor how to run the containers. A Pod's contents are always co-located and co-scheduled, and run in a shared context. A Pod models an application-specific "logical host": it contains one or more application containers which are relatively tightly coupled. In non-cloud contexts, applications executed on the same physical or virtual machine are analogous to cloud applications executed on the same logical host.

### O que são ConfigMaps ? 
A ConfigMap is an API object used to store non-confidential data in key-value pairs. Pods can consume ConfigMaps as environment variables, command-line arguments, or as configuration files in a volume. A ConfigMap allows you to decouple environment-specific configuration from your container images, so that your applications are easily portable.

### About Project

The project has a Spotify plugin to automatically generate the docker image. Every project has its Dockerfile. To generate the service image, you will need to run two commands.

```
mvn clean install
mvn dockerfile:build
```

After generating the artifacts you will need to install MiniKube and kubectl, assuming that these two items are already configured correctly, we select three commands, one to start the kubernetes cluster, enable the kubernetes dashboard that allows tracking the cluster with a visual interface and the other to stop it.

```
minikube start
minikube dashboard
minikube stop
```

For you to be able to deploy your pods with local docker images, you will need to run the command below

```
eval $(minikube -p minikube docker-env)
```

This command directs your minikube to use your local docker-env address in that terminal instance, so your pods will be uploaded using images that have already been built locally without necessarily being on DockerHub or some other image repository.

With the cluster initialized, there are some commands that are important for us to see our pods, deployments, services, etc. Below are two examples of commands to get what is operating in the cluster:

```
kubectl get pods
kubectl get services
```

Our application uses the Postgres database, there is a directory within the project to make the database available on the cluster, you need to run the commands below in sequence:

```
kubectl	create	-f	postgres.yaml
kubectl	create	-f	postgres-service.yaml
kubectl	port-forward svc/postgres 5000:5432
kubectl	create -f config-map.yaml
```

The respective commands create a deployment, a service, perform a database port forwarding inside k8s and create a configmap for the database.

### Deploying the application on Kubernetes
After preparing our dependencies, we will deploy our three services that make up the application. Each service will have a directory with the files related to the creation of config map, deployments and services. This is an important order to keep for the perfect execution of services on k8s.

```
kubectl	create -f configmap.yaml
kubectl create -f deployment.yaml
kubectl create -f service.yaml
```

Running everything in this sequence will successfully run the services in Kubernetes. In case something fails here follow some commands to kill the pod, the deployment and the service.

```
kubectl delete deployment
kubectl delete service
```

Finally, to follow the logs of your application, you need to run the command:

```
kubectl logs {pod name}
```


Here we finish a Project with three microservices and a database all running inside the Kubernetes cluster, where we can manage services and functionalities provided by the cluster.








