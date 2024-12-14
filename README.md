// Command to Run application in dev mode 
./mvnw spring-boot:run "-Dspring-boot.run.profiles=dev"
aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 394226507743.dkr.ecr.us-east-1.amazonaws.com
docker build -t kayzej/users .
docker tag kayzej/users:latest 394226507743.dkr.ecr.us-east-1.amazonaws.com/kayzej/users:latest
docker push 394226507743.dkr.ecr.us-east-1.amazonaws.com/kayzej/users:latest