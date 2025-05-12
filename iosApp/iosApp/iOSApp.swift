import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        NapierHelperKt.doInitNapier()
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
