package bridge

import bridge.view.InputView
import bridge.view.OutputView

val outputView = OutputView()
val inputView = InputView()
val bridgeGame = BridgeGame()
val gamsHost = GameHost(BridgeMaker(BridgeRandomNumberGenerator()), BridgeGame())

fun main() {
    startGame()
    makeBridge()
}

private fun startGame() {
    outputView.printGameStartPhrase()
    println()
}

private fun makeBridge() {
    outputView.printBridgeSizePhrase()
    gamsHost.makeBridge(inputView.readBridgeSize())
    println()
}

private fun progressStage(): String {
    outputView.printMovingBridgeSelectPhrase()
    val userInput = inputView.readMoving()
    val stageState = gamsHost.progressStage(userInput)
    printStageMap(gamsHost.getBridgeCurrentPosition(), gamsHost.getBridge(), stageState)
    println()
    return userInput
}

private fun printStageMap(bridgeCurrentPosition: Int, bridge: List<String>, stageState: Boolean) {
    outputView.printMap(
        stageState,
        bridgeCurrentPosition,
        bridge
    )
}

private fun getProperGameState() {
    outputView.printRestartOrQuitSelectPhrase()
    val userGameCommandInput = inputView.readGameCommand()
    gamsHost.getProperGameState(userGameCommandInput)
}

private fun printGameResultMap(bridgeCurrentPosition: Int, bridge: List<String>, lastUserInput: String) {
    outputView.printFinalGamsResultPhrase()
    printStageMap(bridgeCurrentPosition, bridge, lastUserInput)
    println()
}
