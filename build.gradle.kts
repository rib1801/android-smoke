// Plugins fixados na raiz: versão única para o projeto inteiro.
// AGP 9 tem Kotlin embutido (built-in Kotlin) — o plugin org.jetbrains.kotlin.android
// é proibido aqui e quebra o build se aplicado. O plugin do compilador Compose
// continua sendo necessário e separado.
plugins {
    id("com.android.application") version "9.5.1" apply false
    id("org.jetbrains.kotlin.plugin.compose") version "2.4.21" apply false
}
