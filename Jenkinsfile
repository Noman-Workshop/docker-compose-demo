// Jenkins declarative pipeline
pipeline {
	agent any
	stages {
		stage('Build') {
			steps {
				sh '''
					docker compose build --no-cache --force-rm --pull --parallel
				'''
			}
		}
		stage('Test') {
			steps {
				sh 'echo "Testing..."'
			}
		}
		stage('Deploy') {
			steps {
				sh 'echo "Deploying..."'
			}
		}
	}
}