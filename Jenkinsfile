pipeline {
	agent any

	stages {
		stage('Build') {
			steps {
				sh '''
					echo "Building3....."
				'''
			}
		}
		stage('Test') {
			steps {
				sh '''
					echo "Testing3...";
					exit 1;
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