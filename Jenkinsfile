pipeline {
    agent any

    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub-credentials')
        DOCKER_IMAGE = "mahimadod/lms-authenticator-service"
        JAVA_HOME = tool name: 'JDK21', type: 'jdk'
        MAVEN_HOME = tool name: 'Maven3.9.9', type: 'maven'
    }

    tools {
        maven 'Maven3.9.9'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                deleteDir()
            }
        }

        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/mahimadod/lms-authenticator-service.git'
            }
        }

        stage('Build & Test') {
            steps {
                withCredentials([string(credentialsId: 'github-token', variable: 'GITHUB_TOKEN')]) {
                    configFileProvider([configFile(fileId: 'github-settings', variable: 'MAVEN_SETTINGS')]) {
                        bat """
                            echo JAVA_HOME=%JAVA_HOME%
                            java -version
                            set JAVA_HOME=${env.JAVA_HOME}
                            set MAVEN_HOME=${env.MAVEN_HOME}
                            set PATH=%JAVA_HOME%\\bin;%MAVEN_HOME%\\bin;%PATH%
                            mvn clean install --settings %MAVEN_SETTINGS%
                        """
                    }
                }
            }
            post {
                always {
                    // ✅ MAKE SURE THIS PATTERN MATCHES YOUR FILES!
                    junit '**/target/surefire-reports/*.xml'
                    archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
                }
            }
        }

                stage('Docker Build & Push') {
                    when {
                        expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' }
                    }
                    steps {
                        script {
                            // 🔍 Echo which credential is being used
                            echo "🧪 Using DockerHub credentials ID: dockerhub-credentials"

                            // 🔐 Test docker login manually before using docker.withRegistry
                            withCredentials([usernamePassword(credentialsId: 'dockerhub-credentials', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                                echo "🧪 Attempting manual docker login for user: ${DOCKER_USER}"
                                bat """
                                    echo Logging in to DockerHub as %DOCKER_USER%
                                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                                """
                            }

                            // ✅ Now proceed with Docker build and push if login worked
                            docker.withRegistry('https://registry.hub.docker.com', 'dockerhub-credentials') {
                                def image = docker.build("${DOCKER_IMAGE}:${env.BUILD_NUMBER}")
                                image.push()
                                image.tag('latest')
                                image.push('latest')
                            }
                        }
                    }
                }

        stage('Deploy') {
            when {
                expression { currentBuild.result == null || currentBuild.result == 'SUCCESS' } // ✅ Skip if previous failed
            }
            steps {
                echo 'Deploying Docker container...'
                bat """
                    docker rm -f lms-authenticator || exit 0
                    docker run -d --name lms-authenticator --network lms-network -p 8091:8091 ${DOCKER_IMAGE}:${BUILD_NUMBER}
                """
            }
        }
    }
}