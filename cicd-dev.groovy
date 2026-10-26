node('linux') {
  stage ('Poll') {
    checkout([
      $class: 'GitSCM', branches: [[name: '*/main']], extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/rpmdevtoolsport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [
      string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/rpmdevtoolsport.git'),
      string(name: 'PORT_DESCRIPTION', value: 'This package contains scripts and Emacs support files to aid in the development of RPM packages.'),
      string(name: 'BUILD_LINE', value: 'DEV'),
      booleanParam(name: 'PUBLISH_PYTHON_WHEEL', value: false)
    ]
  }
}
