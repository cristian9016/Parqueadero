pipeline{
    //donde se va a ejecutar el pipeline
    agent{
        label 'Slave_Induccion'
    }
    //opciones espcificas de pipeline dentro del pipeline
    options{
        //mantener artedfactos de salida de consola para el numero especifico de ejecuciones recientes del pipeline
        buildDiscarder(logRotator(numToKeepStr:'3'))
        //no permitir ejecuciones concurrentes de puiupeline
        disableConcurrentBuilds()
    }

    //aqui comienzan los items del pipeline
    stages{
        stage('Checkout'){
            steps{
                echo "--------Checkout-------"
                checkout([
                $class: 'GitSCM',
                branches: [[name: 'master']],
                doGenerateSubmoduleConfigurations: false,
                extensions: [],
                gitTool: 'Git_Centos',
                submoduleCfg: [],
                userRemoteConfigs: [[credentialsId: 'GitHub_cristian9016',
                url: 'https://github.com/cristian9016/Parqueadero.git']]])
            }
        }
        stage('Compile'){
            steps{
                echo "-------Compile----------"
                sh 'chmod u+x gradlew'
                sh './gradlew clean'
                sh './gradlew build --stacktrace'
            }
        }
        stage('Unit Test'){
                    steps{
                        echo "-------UnitTest---------"
                        sh './gradlew test'
                    }
        }
        stage('Static Code Analysis'){
            steps{
                echo "------Analisis de codigo estatico------"
                withSonarQubeEnv('Sonar'){
                    sh "${tool name: 'SonarScanner', type:'hudson.plugins.sonar.SonnarRunnerInstallation'}/bin/sonar-scanner -Dproject.settings=sonar-project.properties"}
            }
        }
        stage('Build'){
            steps{
                echo "---------------build------------"
                sh './gradlew build -x test'
            }
        }
    }
    post{
        always{
            echo 'esto siempre corre'
        }
        success{
            echo 'esto solo corre si fue exitoso'
            junit 'app/build/test-results/test/*.xml'
        }
        failure {
        echo 'This will run only if failed'
        mail (to:'cristian.munoz@ceiba.com.co​',subject: "​Failed Pipeline:${currentBuild.fullDisplayName}​",body: "​Something is wrong with ${env.BUILD_URL}​")
         }
        unstable{
            echo 'esto solo corre si la ejecucion fue marcada como inestable'
        }
        changed{
            echo 'esto solo corre si el estado del pipeline ha cambiado'
            echo 'ejemplo: si el pipeline fallo previamente pero ahora es exitoso'
        }
    }
}