#!/bin/bash

SERVICES=("Backend" "Frontend" "DevOps" "QA" "DataScience" "Security" "ML" "Cloud" "Mobile" "Support")

for i in $(seq 1 100); do
  SERVICE=${SERVICES[$((($i - 1) % 10))]}
  ETAT=$([ $(($i % 2)) -eq 0 ] && echo "true" || echo "false")

  curl -s -o /dev/null -w "Job $i: HTTP %{http_code}\n" \
    -X POST http://localhost:8081/jobs/send \
    -H "Content-Type: application/json" \
    -d "{\"Service\": \"${SERVICE} ${i}\", \"Etat\": ${ETAT}}"
done
