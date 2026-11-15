# android-smoke

App descartável para validar a esteira: commit → GitHub Actions → APK assinado → GitHub Release → atualização automática no celular via Obtainium.

Se o contador abre e o rodapé mostra `versionCode` novo depois de um update, a esteira funciona. Só isso.

## Canais

| branch | saída | usa pra |
|---|---|---|
| `main` | release `v0.1.N` | versão estável |
| `beta` | prerelease `beta-N` | testar antes de promover |
| `dev` | nada | trabalho em andamento |

`versionCode` = número do run do Actions. Sempre crescente, nunca repete, nunca na mão.

## Instalar no celular

1. Instale o [Obtainium](https://github.com/ImranR98/Obtainium/releases) (uma vez, manualmente).
2. No Obtainium: **Add App** → cole `https://github.com/rib1801/android-smoke`.
3. Ele passa a checar releases e instalar updates sozinho.

Samsung: desative **Auto Blocker** (Configurações → Segurança e privacidade) e permita instalação de fontes desconhecidas para o Obtainium.

## Segredos

`KEYSTORE_BASE64`, `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD` — configurados em Settings → Secrets and variables → Actions.

Perder a keystore significa nunca mais conseguir atualizar o app instalado. Backup fora do GitHub é obrigatório.

## Documentação

- `docs/spec.md` — o que o app faz
- `docs/pipeline.md` — como a esteira foi montada e o que não mexer
