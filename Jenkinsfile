pipeline {
	agent any

	stages {
		stage('Build') {
			steps {
				sh '''
					docker compose build -p nbr --no-cache --force-rm
				'''
			}
		}
		stage('Test') {
			steps {
				sh '''
					echo "Testing3...";
				'''
			}
		}
		stage('Deploy') {
			steps {
				sh '''
					echo "Deploying3..."
				'''
			}
		}
	}
}