# Atualize o sub módulo de teste (Caso os instrutores pedirem)
```bash
git submodule update --remote asaas-tech-lab-test
```

# Como rodar todos os testes (Localmente)
```bash
cd asaas-tech-lab-test/
chmod +x ./local-test.sh # concede permissao para executar
./local-test.sh
```

# Obter resultados dos testes
```bash
cat ./asaas-tech-lab-test/results.json
```