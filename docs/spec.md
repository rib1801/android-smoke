# Spec — Smoke Test

Status: usado apenas para provar a esteira de build. Não é um produto.

## O que faz

Uma tela única com:

- contador que começa em 0;
- botão **Somar 1** incrementa;
- botão **Zerar** volta para 0;
- marcação "auto-update OK";
- rodapé mostra `VERSION_NAME` e `VERSION_CODE` do build instalado.

O rodapé é o teste de verdade: se os números mudam depois de uma atualização automática, a esteira inteira (commit → CI → APK assinado → release → Obtainium) funcionou.

## Fora de escopo

Qualquer feature de produto. Ideias novas vão para repos novos criados a partir deste template.

## Histórico

- 0.1.1 — primeira versão, usada para validar build assinado + release automático.
- 0.1.5 — instalada no celular via Obtainium (Samsung S26 Ultra).
- 0.1.6 — adiciona marcação "auto-update OK" para provar atualização automática.
